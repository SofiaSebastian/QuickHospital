package quickhospital.XML;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import quickhospital.pojos.Doctor;
import quickhospital.pojos.Patient;


public class XML{
	public static void doctor2xml(ArrayList<Doctor> doctors) {
		for(int i=0; i<doctors.size();i++) {
			Doctor d = doctors.get(i);
			JAXBContext jaxbContext;
			try {
				jaxbContext = JAXBContext.newInstance(Doctor.class);
				Marshaller marshaller = jaxbContext.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				File file = new File("./xmls/Doctor.xml");
				marshaller.marshal(d,file);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
		
		public static void patient2xml(ArrayList<Patient> patients) {
			for(int i=0; i<patients.size();i++) {
				Patient p = patients.get(i);
				JAXBContext jaxbContext;
				try {
					jaxbContext = JAXBContext.newInstance(Patient.class);
					Marshaller marshaller = jaxbContext.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
					File file = new File("./xmls/Patient.xml");
					marshaller.marshal(p,file);
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
	}
		
		
}
