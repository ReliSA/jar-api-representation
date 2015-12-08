package zcu.jaranalyzer;

import cz.zcu.kiv.jacc.javatypes.*;
import cz.zcu.kiv.jacc.loader.JClassLoaderFacade;
import cz.zcu.kiv.jacc.loader.JClassLoaderFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Set;

public class Main {

    static final String EXPORT = "export";
    static final String IMPORT = "import";

    static void summaryAndExport(Set<JClass> classes, String jarName, PrintStream ps) throws JAXBException {
        AnalyzerSummary as = new AnalyzerSummary();
        as.setJarName(jarName);
        for(JClass jc : classes){
            as.getClasses().add(AnalyzerClass.ConvertFromJClass(jc));
        }

        JAXBContext context = JAXBContext.newInstance(AnalyzerSummary.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(as, ps);
    }

    public static void main(String [] args){
    	if (args.length < 2){
            System.out.println("Too few params. Usage: program <path to jar> <export|import> [output path]");
            return;
        }
        File jarFile = new File(args[0]);
        JClassLoaderFacade facade = JClassLoaderFactory.createVariableDSFacade(null);
        JBlackBoxComponent component = facade.getAPI(jarFile);
        Set<JClass> classes;
        if(args[1].equals(EXPORT)){
            classes = component.getExports();
        }else if(args[1].equals(IMPORT)){
            classes = component.getImports();
        }else{
            System.out.println("Usage: program <path to jar> <export|import> [output path]");
            return;
        }

        if(args.length > 2){
            try {
                summaryAndExport(classes, jarFile.getName(), new PrintStream (new FileOutputStream(args[2], true)));
            } catch (JAXBException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            try {
                summaryAndExport(classes, jarFile.getName(), System.out);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }


    }
}
