package Business.concretes;



import Adapters.GoogleKayitManager;
import Adapters.GooleKayitService;
import Business.abstracts.CheckFirstNameService;
import Business.abstracts.CheckLastNameService;
import Business.abstracts.DogrulamaService;
import Business.abstracts.EmailAktifEkmeService;
import Business.abstracts.MailCheckService;
import Business.abstracts.PasswordCheckService;
import Entites.abstracts.CustomerService;
import Entites.concretes.Customer;

public class CustomerManager implements CustomerService{
	
	private MailCheckService mailCheck;
	private PasswordCheckService passwordCheck;
	private DogrulamaService dogrulama;
	private EmailAktifEkmeService aktifet;
	private GooleKayitService google;
	private CheckFirstNameService checkname;
	private CheckLastNameService checkname2;
	

	

	public CustomerManager(MailCheckService mailCheck, PasswordCheckService passwordCheck, DogrulamaService dogrulama,
			EmailAktifEkmeService aktifet,GooleKayitService google,CheckFirstNameService checkname,CheckLastNameService checkname2) {
		super();
		this.mailCheck = mailCheck;
		this.passwordCheck = passwordCheck;
		this.dogrulama = dogrulama;
		this.aktifet = aktifet;
		this.google=google;
		this.checkname=checkname;
	   
		this.checkname2=checkname2;
		
	}

	
    


	@Override
	public void save(Customer customer) {
		if (mailCheck.checkMail(customer)==true && passwordCheck.passwordCheck(customer)==true && checkname2.checkLastName(customer)==true) {
			dogrulama.dogrula(customer);
			if (aktifet.aktifEt(customer)==true) {
				System.out.println("Emailiniz aktif edildi. " +customer.getEmail());
				System.out.println("Sisteme kay�t edildiniz. " + customer.getFirstName());
			}else {
				System.out.println("Kay�t i�leminin tamamlanmas� i�in l�tfen emailinizi do�rulay�n�z. ");
			}
		}
		else {
			System.out.println("L�tfen bilgilerinizi kontrol ediniz: " 
					+ "\n"+ "-> Ad ve soyad en az iki, parola en az 6 haneli olmal�d�r."
					+"\n "+ "-> Eposta alan� eposta formunda olmal�d�r.");
		}
	
		
	}
	



	@Override
	public void delete(Customer customer) {
		System.out.println("Hesab�n�z silindi.");
		
	}

	@Override
	public void upDate(Customer customer) {
		if (mailCheck.checkMail(customer)==true && passwordCheck.passwordCheck(customer)==true ) {
			System.out.println("Bilgileriniz g�ncellendi. " + customer.getFirstName());
		}
		else {
			System.out.println(" Ge�ersiz email ya da �ifre girdiniz. L�tfen bilgilerinizi kontrol ediniz. �ifrenin en az 6 haneli olmas�na dikkat ediniz.");
		}
	
		
	}
	
	public void saveG(Customer customer) {
		google.saveG("Google hesab� ile giri� yap�ld� " + customer.getEmail());
		
	}



	@Override
	public void LoginIn(Customer customer) {
		if (mailCheck.checkMail(customer)==true && passwordCheck.passwordCheck(customer)==true ) {
			System.out.println("Giri� yap�ld�. " + customer.getFirstName());
		}else {
			System.out.println("Bilgilerinizi kontrol ediniz. Email ve parolan�n eksiz olmas�na dikkat ediniz. ");
		}
		
	}
	



}
