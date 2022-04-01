package requestsystem.commands.vouchers;

import java.util.Map;

import billingsystem.CouponVisitor;
import requestsystem.commands.CommandTemplate;

public class ValidateVoucherCommand extends CommandTemplate<CouponVisitor> {

    private static final String MUTATION_NAME = "removeVoucher";
	private static final String UNDO_MUTATION_NAME = "createVoucher";
	private CouponVisitor couponvisitor;

    public ValidateVoucherCommand(CouponVisitor couponvisitor){
		this.couponvisitor = couponvisitor;
	}
    
    @Override
    public String createMessage(boolean undo) {
        // Undo doesn't apply to query types.
		return String.format("{\"query\":\"query{%s(id: \\\"%s\\\"){id type amount issue_date expiry_date available{id}}}\"}", couponvisitor.CodeGet());
    }

    @Override
    public void parseResponse(Map<?, ?> response) {
        String mutation;
		if (response.containsKey(MUTATION_NAME))
		{
			mutation = MUTATION_NAME;
		}
		else if (response.containsKey(UNDO_MUTATION_NAME))
		{
			mutation = UNDO_MUTATION_NAME;
		}
		else
		{
			// Break if no acceptable response is returned
			return;
		}

		Map<?, ?> voucherData = (Map<?, ?>) response.get(mutation);
		String id = (String) voucherData.get("id");
		String type = (String) voucherData.get("type");
		double amount = (double) voucherData.get("amount");
		Map<?,?> reservationData = (Map<?, ?>) voucherData.get("available");
		if(reservationData.containsKey("id") && reservationData.get("id") != null){
			responseObject = new CouponVisitor(id, type, amount, true);
		} else {
			responseObject = new CouponVisitor(id, type, amount, false);
		}		
		// Make a copy for undo
		this.couponvisitor = responseObject;
    }
}
