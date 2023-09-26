package Enums;

public enum Queries {

    CREATE_MYSTUDENT_TABLE("create table MyStudents("+"id bigint primary key not null," +
            "name varchar(30) not null," +
            "surname varchar(30) not null," +
            "age int not null)"),
    INSERT_STUDENT("insert into mystudents(id,name,surname,age) " +
            "values(?,?,?,?)"),
    GET_STUDENT_BY_ID("select * from mystudents where id=?"),
    UPDATE_STUDENT_NAME("update mystudents set name=? where id=?"),
    DELETE_STUDENT("Delete from mystudents where id=? "),
    GET_ALL_STUDENTS("Select * from mystudents");





    private final String query;

    Queries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

}
