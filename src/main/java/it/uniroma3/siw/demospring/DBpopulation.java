package it.uniroma3.siw.demospring;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.demospring.model.Album;
import it.uniroma3.siw.demospring.model.Photo;
import it.uniroma3.siw.demospring.model.Photographer;
import it.uniroma3.siw.demospring.repository.AlbumRepository;
import it.uniroma3.siw.demospring.repository.PhotoRepository;
import it.uniroma3.siw.demospring.repository.PhotographerRepository;
import it.uniroma3.siw.demospring.repository.RequestRepository;

@Component
public class DBpopulation implements ApplicationRunner{

	@Autowired
	private PhotoRepository pr;
	@Autowired
	private PhotographerRepository phr;
	@Autowired
	private AlbumRepository ar;
	@Autowired
	private RequestRepository rr;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		this.addAll();
	}
	private void addAll() {
		//paesaggi
		String p1 ="https://www.dimanoinmano.it/img/393158/full/arte/ottocento/paesaggio-di-eugenio-gignous.jpg";
		String p2 ="https://www.dimanoinmano.it/img/401429/full/arte/novecento/paesaggio-montano-di-romeo-borgognoni.jpg";
		String p3 ="https://weshoot.s3.amazonaws.com/2037/Workshop_photography_events_723feefaf7cb5ac3c901f069519658c6_thumb.jpg";
		String p4 ="https://www.radio-roseto.com/radio-roseto.com/wp-content/uploads/2018/11/Pianocinquemiglia-prima-e-dopo-2.jpg";
		//nature morte
		String p5 ="http://www.ioarte.org/img/artisti/Cardellarte__Natura-morta-3_g.jpg";
		String p6 ="http://www.dimanoinmano.it//img/369036/full/arte/novecento/natura-morta-con-melograni-di-ernesto-alcide-campestrini.jpg";
		String p7 ="https://www.dimanoinmano.it/img/380293/full/arte/novecento/ampelio-tettamanti-natura-morta.jpg";
		String p8 ="https://www.dimanoinmano.it/img/390850/full/arte/pittura-antica/natura-morta-con-frutta.jpg";
		//mare
		String p9 ="https://www.bedandbreakfast-osimo.it/images/mare/parco-conero.jpg";
		String p10 ="http://www.th-resorts.com/wp-content/uploads/LisciaEldi01.jpg";
		String p11 ="https://dhqbz5vfue3y3.cloudfront.net/fotobbit/40382/1/40382_square.jpg?rfh=0";
		String p12 ="https://www.bedandbreakfast-osimo.it/images/mare/numana.jpg";
		//orologi
		String p13 ="https://www.paolosaba.it/2722-large_default/philip-watch-orologio-donna-marilyn-silver-r8253596510.jpg";
		String p14 ="http://www.criscenzogioielli.com/wp-content/uploads/2015/12/Orologio-Colonna-Pois-2-600x600.jpg";
		String p15 ="https://www.garmimarine.it/media/catalogo/prodotti/o/orologio-garmin-quatix-5-sapphire-edition-600.jpg";
		String p16 ="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR9ZRGMSm6-2oh9M_SqLE04H184F4cY-p64n8exlLPBhcWqqbxWjg";
		//fotografi
		Photographer ph1 = new Photographer();
		Photographer ph2 = new Photographer();
		Photographer ph3 = new Photographer();
		Photographer ph4 = new Photographer();
		//dettagli fotografi
		ph1.setName("Mario"); ph1.setSurname("Rossi");		phr.save(ph1);
		ph2.setName("Giuseppe"); ph2.setSurname("Verdi");	phr.save(ph2);
		ph3.setName("Andrea"); ph3.setSurname("Speranza");	phr.save(ph3);
		ph4.setName("Luca"); ph4.setSurname("Cabibbo");		phr.save(ph4);
		//foto
		Photo f1 = new Photo();	f1.setPhotographer(ph1);	f1.setName("img1");	f1.setImagePath(p1);	pr.save(f1);
		Photo f2 = new Photo();	f2.setPhotographer(ph1);	f2.setName("img2");	f2.setImagePath(p2);	pr.save(f2);
		Photo f3 = new Photo();	f3.setPhotographer(ph1);	f3.setName("img3");	f3.setImagePath(p3);	pr.save(f3);
		Photo f4 = new Photo();	f4.setPhotographer(ph1);	f4.setName("img4");	f4.setImagePath(p4);	pr.save(f4);
		
		Photo f5 = new Photo();	f5.setPhotographer(ph2);	f5.setName("img5");	f5.setImagePath(p5);	pr.save(f5);
		Photo f6 = new Photo();	f6.setPhotographer(ph2);	f6.setName("img6");	f6.setImagePath(p6);	pr.save(f6);
		Photo f7 = new Photo();	f7.setPhotographer(ph2);	f7.setName("img7");	f7.setImagePath(p7);	pr.save(f7);
		Photo f8 = new Photo();	f8.setPhotographer(ph2);	f8.setName("img8");	f8.setImagePath(p8);	pr.save(f8);
		
		Photo f9 = new Photo();	f9.setPhotographer(ph3);		f9.setName("img9");		f9.setImagePath(p9);	pr.save(f9);
		Photo f10 = new Photo();	f10.setPhotographer(ph3);	f10.setName("img10");	f10.setImagePath(p10);	pr.save(f10);
		Photo f11 = new Photo();	f11.setPhotographer(ph3);	f11.setName("img11");	f11.setImagePath(p11);	pr.save(f11);
		Photo f12 = new Photo();	f12.setPhotographer(ph3);	f12.setName("img12");	f12.setImagePath(p12);	pr.save(f12);
		
		Photo f13 = new Photo();	f13.setPhotographer(ph4);	f13.setName("img13");	f13.setImagePath(p13);	pr.save(f13);
		Photo f14 = new Photo();	f14.setPhotographer(ph4);	f14.setName("img14");	f14.setImagePath(p14);	pr.save(f14);
		Photo f15 = new Photo();	f15.setPhotographer(ph4);	f15.setName("img15");	f15.setImagePath(p15);	pr.save(f15);
		Photo f16 = new Photo();	f16.setPhotographer(ph4);	f16.setName("img16");	f16.setImagePath(p16);	pr.save(f16);
		
		//album
		Album a1 = new Album();	a1.setName("Paesaggi");	a1.setDescription("Foto di paesaggi"); 	a1.setImagePath(p1);
		List<Photo> l1 = new LinkedList<Photo>();	l1.add(f1);	l1.add(f2);	l1.add(f3);	l1.add(f4);	a1.setPhotos(l1);
		ar.save(a1);
		
		Album a2 = new Album();	a2.setName("Nature Morte");	a2.setDescription("Foto di nature morte"); 	a2.setImagePath(p5);
		List<Photo> l2 = new LinkedList<Photo>();	l2.add(f5);	l2.add(f6);	l2.add(f7);	l2.add(f8);	a2.setPhotos(l2);
		ar.save(a2);
		
		Album a3 = new Album();	a3.setName("Mare");	a3.setDescription("Foto del mare"); 	a3.setImagePath(p9);
		List<Photo> l3 = new LinkedList<Photo>();	l3.add(f9);	l3.add(f10);	l3.add(f11);	l3.add(f12);	a3.setPhotos(l3);
		ar.save(a3);
		
		Album a4 = new Album();	a4.setName("Orologi");	a4.setDescription("Foto di orologi"); 	a4.setImagePath(p13);
		List<Photo> l4 = new LinkedList<Photo>();	l4.add(f13);	l4.add(f14);	l4.add(f15);	l4.add(f16);	a4.setPhotos(l4);
		ar.save(a4);
		
	}


}