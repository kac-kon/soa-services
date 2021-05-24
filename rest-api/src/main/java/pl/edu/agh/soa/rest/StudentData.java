package pl.edu.agh.soa.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import io.swagger.annotations.*;
import pl.edu.agh.soa.model.Student;

@Path("/StudentData/")
@Api(value = "Students API")
public class StudentData {
    private static final List<Student> students = new Student().generate();

    @GET
    @Path("/{index}")
    @Produces("application/json")
    @ApiOperation("Returns student with specified index")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Student not found")
    })
    public Response findByIndex(
            @ApiParam(required = true) @PathParam("index") int index
    ) {
        for (Student x: students){
            if (index == x.getIndex()){
                return Response.status(Response.Status.OK).entity(x).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/")
    @Produces("application/json")
    @ApiOperation("Returns all students")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Students found"),
            @ApiResponse(code = 404, message = "No students found")
    })
    public Response getStudents() {
//        students.add(new Student("123", 123, Collections.singletonList("ss")));
        if (students.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(students).build();
    }


    @POST
    @Path("/")
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation("Adds student")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Student added"),
            @ApiResponse(code = 406, message = "Bad student format")
    })
    public Response addStudent(
            @ApiParam(required = true, name = "new student") Student student
    ) {
        try {
            students.add(student);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @PUT
    @Path("/{index}")
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation("Updates student's info by given index")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Student updated"),
            @ApiResponse(code = 404, message = "Student not found")
    })
    public Response update(
            @ApiParam(required = true) @PathParam("index") int index,
            @ApiParam(required = true, name = "New Student") Student student
    ) {
        if (student.getIndex() == index) {
            for (Student x: students) {
                if (index == (x.getIndex())) {
                    x.setSubjects(student.getSubjects());
                    x.setName(student.getName());
                    return Response.status(Response.Status.ACCEPTED).build();
                }
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{index}")
    @ApiOperation("Deletes student with given index")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Student deleted"),
            @ApiResponse(code = 404, message = "Student not found")
    })
    public Response deleteStudent(@ApiParam(required = true) @PathParam("index") int index) {
        students.removeIf(x -> index == (x.getIndex()));

        return Response.status(Response.Status.OK).build();
    }

}
