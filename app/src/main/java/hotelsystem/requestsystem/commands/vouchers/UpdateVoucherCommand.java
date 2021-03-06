package hotelsystem.requestsystem.commands.vouchers;

import java.util.Map;

import hotelsystem.billingsystem.CouponVisitor;
import hotelsystem.requestsystem.commands.CommandTemplate;
import hotelsystem.userinterface.LoginUI;

/**
 * @author Aleksandr Jakusevs
 * Command for updating a voucher within the system
 */
public class UpdateVoucherCommand extends CommandTemplate<CouponVisitor> {

    private static final String MUTATION_NAME = "updateVoucher";
	private static final String UNDO_MUTATION_NAME = "updateVoucher";
	private CouponVisitor couponvisitor;

	/**
	 * Simple constructor for the UpdateVoucherCommand
	 * @param couponvisitor the voucher/coupan
	 */
    public UpdateVoucherCommand(CouponVisitor couponvisitor){
		this.couponvisitor = couponvisitor;
	}
    
    @Override
    public String createMessage(boolean undo) {
		return String.format("{\"query\":\"mutation{%s(id: \\\"%s\\\" voucher: {type: \\\"%s\\\" amount: %f creator:{id: %d} available:{id: %d}}){id type issue_date expiry_date amount creator{id}}}\"}", MUTATION_NAME, couponvisitor.CodeGet(), couponvisitor.TypeGet(), couponvisitor.DiscountGet(), LoginUI.getUser().getId(), couponvisitor.ReservationGet());
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
			int reservationId = Integer.parseInt((String) reservationData.get("id"));
			responseObject = new CouponVisitor(id, type, amount, true, reservationId);
		} else {
			responseObject = new CouponVisitor(id, type, amount, false);
		}		
		// Make a copy for undo
		couponvisitor = responseObject;
    }
}
