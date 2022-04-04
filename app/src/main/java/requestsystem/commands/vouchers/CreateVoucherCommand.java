package requestsystem.commands.vouchers;

import java.util.Map;

import billingsystem.CouponVisitor;
import requestsystem.commands.CommandTemplate;
import userinterface.LoginUI;

public class CreateVoucherCommand extends CommandTemplate<CouponVisitor> {

	private static final String MUTATION_NAME = "createVoucher";
	private static final String UNDO_MUTATION_NAME = "removeVoucher";
	private CouponVisitor couponvisitor;

	public CreateVoucherCommand(CouponVisitor couponvisitor){
		this.couponvisitor = couponvisitor;
	}

    @Override
    public String createMessage(boolean undo) {
        
		if (undo)
		{
			return String.format("{\"query\":\"mutation{%s(input:{id: \\\"%s\\\"}){id type amount available{id}}}\"}", UNDO_MUTATION_NAME, couponvisitor.CodeGet());
		}

		return String.format("{\"query\":\"mutation{%s(input:{type: \\\"%s\\\" amount: %f creator:{id: %d}}){id type issue_date expiry_date amount creator{id}}}\"}", MUTATION_NAME, couponvisitor.TypeGet(), couponvisitor.DiscountGet(), LoginUI.getUser().getId());
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
		if(reservationData != null && reservationData.containsKey("id") && reservationData.get("id") != null){
			responseObject = new CouponVisitor(id, type, amount, true);
		} else {
			responseObject = new CouponVisitor(id, type, amount, false);
		}		
		
		// Make a copy for undo
		this.couponvisitor = responseObject;  
    }
}
