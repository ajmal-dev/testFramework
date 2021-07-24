package test.java.apiAutomation.tests.Graphql.Graphqlcc.pojo;

public class CcOrderInput
{

    private String orderId;
    private String apimXUserId;
    private String externalId;
    private String orderState;

    public CcOrderInput(String orderId, String apimXUserId, String externalId, String orderState)
    {
        this.orderId = orderId;
        this.apimXUserId = apimXUserId;
        this.externalId = externalId;
        this.orderState = orderState;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getApimXUserId() {
        return apimXUserId;
    }

    public String getExternalId() {
        return externalId;
    }

    public String getOrderState() {
        return orderState;
    }

    //setters


    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setApimXUserId(String apimXUserId) {
        this.apimXUserId = apimXUserId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }
}
