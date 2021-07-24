package test.java.apiAutomation.tests.Graphql.Graphqlcc.pojo;

public class CreateCcOrderPayload

{

    private String query;
    VariablesCreateCc variables;
    private String operationName;

    public CreateCcOrderPayload(String query, VariablesCreateCc variables, String operationName)
    {
        this.query = query;
        this.variables = variables;
        this.operationName = operationName;
    }

    //getters

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public VariablesCreateCc getVariables() {
        return variables;
    }

    public void setVariables(VariablesCreateCc variables) {
        this.variables = variables;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}
