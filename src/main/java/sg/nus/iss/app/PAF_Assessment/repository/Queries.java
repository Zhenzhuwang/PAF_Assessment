package sg.nus.iss.app.PAF_Assessment.repository;

public class Queries {

    public static final String SQL_INSERT_ACCOUNT_TABLE = """
        insert into account(account_id, name, balance) values 
        ( ?, ? ,?)
        """;
    
}
