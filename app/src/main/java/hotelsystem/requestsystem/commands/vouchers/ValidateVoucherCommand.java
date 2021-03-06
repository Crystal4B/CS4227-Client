package hotelsystem.requestsystem.commands.vouchers;

import java.util.Map;

import hotelsystem.billingsystem.CouponVisitor;
import hotelsystem.requestsystem.commands.CommandTemplate;

/**
 * @author Aleksandr Jakusevs
 * Command for validating a voucher within the system
 */
public class ValidateVoucherCommand extends CommandTemplate<CouponVisitor> {

    private static final String QUERY_NAME = "validateVoucher";
	private CouponVisitor couponvisitor;

	/**
	 * Simple constructor for the ValidateVoucherCommand
	 * @param couponvisitor the voucher/coupan
	 */
    public ValidateVoucherCommand(CouponVisitor couponvisitor){
		this.couponvisitor = couponvisitor;
	}
    
    @Override
    public String createMessage(boolean undo) {
        // Undo doesn't apply to query types.
		return String.format("{\"query\":\"query{%s(id: \\\"%s\\\"){id type amount issue_date expiry_date available{id}}}\"}", QUERY_NAME, couponvisitor.CodeGet());
    }

    @Override
    public void parseResponse(Map<?, ?> response) {
        String mutation;
		if (response.containsKey(QUERY_NAME))
		{
			mutation = QUERY_NAME;
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
