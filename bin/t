Classe application rappresenta l'intera applicazione, con un main che carica i componenti
__________________________________
Persistenza, Repository:
org.springframework.data.repository.CrudRepository					
	public interface repoName extends CrudRepository<key, value>{
		public List<key> findByColumn(String Column);
	}
__________________________________
Operazioni di Sistema, Services:
import org.springframework.stereotype.Service;
	@Service
	public class StudenteService {
	
	//Cioè si collega all'istanza di studenteRepository, viene "iniettata"
	@Autowired 
	private StudenteRepository studenteRepository;
	
	//Comportamento transazionale
	@Transactional 
	public Studente inserisci(Studente studente) {
		return studenteRepository.save(studente);
	}
	
	
	@Transactional
	public List<Studente> tutti(){
		return (List<Studente>) studenteRepository.findAll();
	}
__________________________________	
SpringBoot ha una architettura MVC basata sul front controller
questo accetta le richiesta http e in base all'url delega la gestione della richiesta a 
classi controller custom che validano i dati, mettono i dati necessari sulla mappa(model), ritornano il nome della prossima vista.
Il controller è definito tramite @Controller
E' possibile mappare ogni metodo del controller con un url tramite @RequestMapping
Ogni metodo torna una stringa che è il nome della vista successiva
L'aggiunta alla mappa si fa tramite model:
	@Controller
	public class StudenteController {
	
	@Autowired
	private StudenteService studenteService;
	
	@Autowired
	private StudenteValidator studenteValidator;
	
	@RequestMapping(value = "/studente", method = RequestMethod.POST)
	public String newStudente(@Valid @ModelAttribute("studente") Studente studente,
			Model model, BindingResult bindingResult) {
		this.studenteValidator.validate(studente, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.studenteService.inserisci(studente);
			model.addAttribute("studenti", this.studenteService.tutti());
			return "studenti.html";
		}else {
			return "studenteForm.html";
		}
	}
	
	@RequestMapping(value = "/studente/{id}", method = RequestMethod.GET)
	public String getStudente(@PathVariable ("id") Long id, Model model) {
		if(id!=null) {
			model.addAttribute("studente", this.studenteService.studentePerId(id));
			return "studente.html";
		}else {
			model.addAttribute("studenti", this.studenteService.tutti());
			return "studenti.html";
		}
	}
__________________________________
Validatori, la validazione avviene tramite classi che implementano il validatore e classi binding
i messaggi di errore sono staticamente memorizzati in un file di testo, con supposto all'internazionalizzazione.

	@Component //deve implementare validator
	public class StudenteValidator implements Validator{

	@Override
	public boolean supports(Class<?> aClass) {
		return Studente.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors error) {
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "cognome", "required");
	}
Il file path va specificato nelle properties:
	spring.messages.basename=messages/validation
In messages scrivere le classi messaggio come testo, in validation.properties:
	required.studente.nome = Mandatory field
	required.studente.cognome = Mandatory field

___________________________________

Thymeleaf
	
	Lista:
	
		<ul>
			<!-- Finchè trovi uno studente, stampa una lista puntata -->
			<li th:each="studente : ${studenti}"> 
				<!-- Viene stampato un link -->
				<!-- Il link lo costruiamo noi, con il nome dello studente, 
					abbiamo indicato che sia il controller a gestire la richiesta,
					con una get-->
				<a
				th:href="@{'/studente' + '/' + ${studente.id}}"
				th:text="${studente.nome}"></a>
			</li>
		</ul>
		
	Form:
	
	<form th:action="@{/studente}" method="POST" th:object="${studente}">
		<table>
			<tr>
				<td>Nome:</td>
				<td><input type="text" th:field="*{nome}" /></td>
				<td th:if="${#fields.hasErrors('nome')}" th:errors="*{nome}"></td>
			</tr>
			<tr>
				<td>Cognome:</td>
				<td><input type="text" th:field="*{cognome}" /></td>
				<td th:if="${#fields.hasErrors('cognome')}" th:errors="*{cognome}"></td>
			</tr>
			<tr>
				<td>
					<button type="submit">Invio</button>
				</td>
			</tr>
		</table>
	</form>

	