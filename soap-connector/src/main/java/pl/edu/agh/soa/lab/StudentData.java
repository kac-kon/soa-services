package pl.edu.agh.soa.lab;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.3.10
 * 2021-04-28T10:26:02.957+02:00
 * Generated source version: 3.3.10
 *
 */
@WebService(targetNamespace = "http://lab.soa.agh.edu.pl/", name = "StudentData")
@XmlSeeAlso({ObjectFactory.class})
public interface StudentData {

    @WebMethod(operationName = "FindAll")
    @RequestWrapper(localName = "FindAll", targetNamespace = "http://lab.soa.agh.edu.pl/", className = "pl.edu.agh.soa.lab.FindAll")
    @ResponseWrapper(localName = "FindAllResponse", targetNamespace = "http://lab.soa.agh.edu.pl/", className = "pl.edu.agh.soa.lab.FindAllResponse")
    @WebResult(name = "students", targetNamespace = "")
    public pl.edu.agh.soa.lab.FindAllResponse.Students findAll()
;

    @WebMethod(operationName = "Add")
    @RequestWrapper(localName = "Add", targetNamespace = "http://lab.soa.agh.edu.pl/", className = "pl.edu.agh.soa.lab.Add")
    @ResponseWrapper(localName = "AddResponse", targetNamespace = "http://lab.soa.agh.edu.pl/", className = "pl.edu.agh.soa.lab.AddResponse")
    public void add(

        @WebParam(name = "name", targetNamespace = "")
        java.lang.String name,
        @WebParam(name = "index", targetNamespace = "")
        java.lang.Integer index,
        @WebParam(name = "subjects", targetNamespace = "")
        java.util.List<java.lang.String> subjects
    );

    @WebMethod(operationName = "Delete")
    @RequestWrapper(localName = "Delete", targetNamespace = "http://lab.soa.agh.edu.pl/", className = "pl.edu.agh.soa.lab.Delete")
    @ResponseWrapper(localName = "DeleteResponse", targetNamespace = "http://lab.soa.agh.edu.pl/", className = "pl.edu.agh.soa.lab.DeleteResponse")
    public void delete(

        @WebParam(name = "index", targetNamespace = "")
        java.lang.Integer index
    );

    @WebMethod(operationName = "Find")
    @RequestWrapper(localName = "Find", targetNamespace = "http://lab.soa.agh.edu.pl/", className = "pl.edu.agh.soa.lab.Find")
    @ResponseWrapper(localName = "FindResponse", targetNamespace = "http://lab.soa.agh.edu.pl/", className = "pl.edu.agh.soa.lab.FindResponse")
    @WebResult(name = "return", targetNamespace = "")
    public pl.edu.agh.soa.lab.Student find(

        @WebParam(name = "index", targetNamespace = "")
        java.lang.Integer index
    );

    @WebMethod(operationName = "SendImage")
    @RequestWrapper(localName = "SendImage", targetNamespace = "http://lab.soa.agh.edu.pl/", className = "pl.edu.agh.soa.lab.SendImage")
    @ResponseWrapper(localName = "SendImageResponse", targetNamespace = "http://lab.soa.agh.edu.pl/", className = "pl.edu.agh.soa.lab.SendImageResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String sendImage(

        @WebParam(name = "path", targetNamespace = "")
        java.lang.String path
    );
}
