package requestsystem.commands.vouchers;

import java.util.Map;

import billingsystem.CouponVisitor;
import requestsystem.commands.CommandTemplate;

public class CreateVoucherCommand extends CommandTemplate<CouponVisitor> {

	private static final String MUTATION_NAME = "createVoucher";
	private static final String UNDO_MUTATION_NAME = "roomRoom";

    @Override
    public String createMessage(boolean undo) {
        // TODO Auto-generated method stub
        return null;
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

		Map<?, ?> roomsData = (Map<?, ?>) response.get(mutation);
		String id = (String) roomsData.get("id");
		String type = (String) roomsData.get("type");
		int numberOfBeds = (int) roomsData.get("numberOfBeds");
		switch(type)
		{
			case "Standard":
			responseObject = new Room(type, Integer.parseInt(id), numberOfBeds);
			break;
		}
		
		// Make a copy for undo
		this.room = responseObject;
        
    }
    
}
