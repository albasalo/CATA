/*
 * Modificado de IndexController.java -
 * 	http://vraptor3.googlecode.com/files/vraptor-blank-project-3.3.1.zip
 */
package br.usp.cata.web.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.usp.cata.model.CataConstraints;
import br.usp.cata.model.User;
import br.usp.cata.service.CryptoService;
import br.usp.cata.service.EmailService.EmailResult;
import br.usp.cata.service.NewUserService;
import br.usp.cata.service.NewUserService.SignupResult;
import br.usp.cata.service.RuleService;
import br.usp.cata.service.UserService;
import br.usp.cata.web.interceptor.IrrestrictAccess;
import br.usp.cata.web.interceptor.Transactional;


@Resource
@IrrestrictAccess
public class IndexController {
	
	private final Result result;
	private final Validator validator;
	private final NewUserService newUserService;
	private final RuleService ruleService;
	private final UserService userService;
	
	public IndexController(Result result, Validator validator,
			NewUserService newUserService, RuleService ruleService, UserService userService) {
		this.result = result;
		this.validator = validator;
		this.newUserService = newUserService;
		this.ruleService = ruleService;
		this.userService = userService;
	}

	@Get
	@Path("/")
	public void index() {
		if(userService.isAuthenticatedUser())
			result.redirectTo(HomeController.class).index();
	}
    
    @Post
    @Path("/login")
    @Transactional
    public void login(User user) {
    	
    	if(user.getEmail().equals(""))
    		validator.add(new ValidationMessage(
    				CataConstraints.emptyField, "E-mail"));
    	if(user.getPassword().equals(""))
    		validator.add(new ValidationMessage(
    				CataConstraints.emptyField, "Senha"));    	
    	validator.onErrorRedirectTo(IndexController.class).index();

        final boolean success = userService.authenticate(user.getEmail(), user.getPassword());

        if(!success) {
        	validator.add(new ValidationMessage("Valores inválidos.", "E-mail ou senha"));
            validator.onErrorRedirectTo(IndexController.class).index();
        }
        
        result.redirectTo(HomeController.class).index();
    }

	@Post
	@Path("/advice")
	public void advice(UploadedFile file) throws Exception {
		if(file == null)
			validator.add(new ValidationMessage(
    				"Selecione um arquivo no formato .txt.", "Nenhum arquivo selecionado"));
		else if(!file.getContentType().equals("text/plain") &&
				!file.getContentType().equals("application/pdf")) {
			validator.add(new ValidationMessage(
					"O arquivo deve estar em formato .txt ou .pdf.", "Formato do arquivo"));
		}
		validator.onErrorRedirectTo(IndexController.class).index();
		
		result.forwardTo(SuggestionsController.class).results(file);
	}
	
	@Get
	@Path("/rules")
	public void rules() {
		result.include("rules", ruleService.findAll());
	}
	
	@Get
	@Path("/about")
	public void about() {
	}
    
    @Get
    @Path("/signup")
    public void signup() {
		if(userService.isAuthenticatedUser())
			result.redirectTo(HomeController.class).index();
    }
    
    @Post
    @Path("/signup")
    @Transactional
    public void signup(User newUser, String password)
    {
    	if(newUser.getName().equals(""))
    		validator.add(new ValidationMessage(
    				CataConstraints.emptyField, "Nome"));
    	if(newUser.getEmail().equals(""))
    		validator.add(new ValidationMessage(
    				CataConstraints.emptyField, "E-mail"));
    	if(newUser.getPassword().length() < CataConstraints.PASSWORD_MIN_LENGTH)
    		validator.add(new ValidationMessage(
    				"A senha deve ter, no mínimo, 6 caracteres.", "Senha"));
    	if(newUser.getPassword().length() > CataConstraints.PASSWORD_MAX_LENGTH)
    		validator.add(new ValidationMessage(
    				"A senha deve ter, no máximo, 32 caracteres", "Senha"));
    	if(!newUser.getPassword().equals(password))
        	validator.add(new ValidationMessage(
        			"As senhas digitadas não são idênticas.", "Senhas"));       
    	validator.onErrorRedirectTo(IndexController.class).signup();
        
    	SignupResult signupResult = newUserService.register(newUser);
    	
    	switch(signupResult) {
    		case SUCCESS:
    			result.include("messages", "Seu acesso foi criado. " +
    					"Siga as instruções enviadas para o endereço " + newUser.getEmail() +
    					" para ativar sua conta.");
    			break;
    		case USER_ALREADY_REGISTERED_ACTIVE_EMAIL:
    			validator.add(new ValidationMessage(
            			"Já existe um usuário cadastrado com este e-mail no sistema.", "E-mail"));
    			break;
    		case USER_ALREADY_REGISTERED_NAME:
    			validator.add(new ValidationMessage(
    					"Já existe um usuário cadastrado com este nome no sistema.", "Nome"));
    		case USER_ALREADY_REGISTERED_INACTIVE:
    			result.include("messages",
    					"Já existe um usuário cadastrado com este e-mail no sistema - mas está inativo. " + 
    					"Um e-mail de ativação foi enviado para o endereço " + newUser.getEmail() + ".");
    			break;
    		case NO_EMAIL_SENT:
    			validator.add(new ValidationMessage(
    					"Não foi possível enviar o e-mail de ativação de conta para o endereço " + newUser.getEmail() + ". " +
    							"Tente novamente mais tarde ou use outro endereço de e-mail.", "E-mail de ativação"));
    			break;
    		default:
    			 throw new IllegalStateException("Unexpected signup result");
    	}    	
    	validator.onErrorRedirectTo(IndexController.class).signup();
    	
    	result.redirectTo(IndexController.class).index();
    }
    
    @Get
    @Path("/signup/activate/{activationKey}")
    @Transactional
    public void activate(String activationKey)
    {
    	SignupResult activationResult = newUserService.activate(activationKey);
    	
    	switch(activationResult) {
    		case SUCCESS:
    			result.include("messages", "Sua conta foi ativada com sucesso.");
    			break;
    		case USER_ALREADY_REGISTERED_ACTIVE_EMAIL:
    			result.include("messages", "Sua conta já está ativada.");
    			break;
    		case ACTIVATION_KEY_NOT_FOUND:
    			validator.add(new ValidationMessage(
        				"Não ocorreu ativação de nenhuma conta porque o link é inválido.", "Link inválido"));
    			break;
    		default:
    			throw new IllegalStateException("Unexpected activation result");
    	}  	
    	validator.onErrorRedirectTo(IndexController.class).index();
    	
    	result.redirectTo(IndexController.class).index();
    }
    
    @Get
    @Path("/recover")
    public void recover() {
    	if(userService.isAuthenticatedUser())
			result.redirectTo(HomeController.class).index();
    }
    
    @Post
    @Path("/recover")
    @Transactional
    public void recover(String email) {
    	User user = userService.findByEmail(email);
    	
    	if(user == null)
    		validator.add(new ValidationMessage(
    				"E-mail inexistente na base de usuários.", "E-mail"));
    	else {
    		EmailResult emailResult = userService.recoverPassword(user);
    		if(emailResult.equals(EmailResult.NO_EMAIL_SENT))
    			validator.add(new ValidationMessage(
    					"Não foi possível enviar o e-mail de ativação de conta para o endereço " + email + ". " +
    							"Tente novamente mais tarde.", "E-mail de renovação de senha"));
    		else
    			result.include("messages", "Um e-mail com instruções para renovação da senha foi" +
    					" enviado para o endereço " + email + ".");
    			
    	}
  	   	validator.onErrorRedirectTo(IndexController.class).recover();
  	   	
    	result.redirectTo(IndexController.class).index();
    }
  
    @Get
    @Path("/recover/newpassword/{newPasswordKey}")
    @Transactional
    public void newPassword(String newPasswordKey)
    {
    	User user = userService.findByNewPasswordKey(newPasswordKey);
    	
    	if(user == null)
    		result.redirectTo(IndexController.class).index();
    	
    	result.include("newPasswordKey", newPasswordKey);
    	result.forwardTo(IndexController.class).newpassword();
    }
    
    @Post
    @Path("/newpassword")
    public void newpassword() {
    }
    
    @Post
    @Path("/changepassword")
    @Transactional
    public void changepassword(String email, String password, String password2, String newPasswordKey) {
    	User user = userService.findByNewPasswordKey(newPasswordKey);
    	
    	if(user == null || !user.getEmail().equals(email) ||
    			!user.getNewPasswordKey().equals(newPasswordKey)) {
    		validator.add(new ValidationMessage("Não houve alteração de senha.",
    				"Você não possui autorização"));
    		validator.onErrorRedirectTo(IndexController.class).index();
    	}    		
    	if(!password.equals(password2)) {
    		validator.add(new ValidationMessage("As senhas digitadas não coincidem.", "Senhas"));
    		result.include(newPasswordKey);
    		validator.onErrorRedirectTo(IndexController.class).newpassword();
    	}
    	
    	user.setPassword(CryptoService.generateMd5(password));
    	user.setNewPasswordKey(null);
    	userService.update(user);
    	
    	result.include("messages", "Sua senha foi alterada com sucesso.");
    	
    	result.redirectTo(IndexController.class).index();
    }
    
    @Get
    @Path("/advanced")
    public void advanced() {
    }
}
