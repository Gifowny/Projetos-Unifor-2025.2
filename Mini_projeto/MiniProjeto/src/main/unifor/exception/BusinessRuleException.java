package exception;


public class BusinessRuleException extends AcademicGatewayException {

    public BusinessRuleException(String message) {
        super(message, "BUSINESS_RULE_VIOLATION");
    }
}