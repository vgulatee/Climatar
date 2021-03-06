package game.climatar.systems.political;

import com.badlogic.gdx.Gdx;

import game.climatar.architecture.Controller;
import game.climatar.architecture.SetModel;
import game.climatar.systems.political.PoliticalSystemModel.PoliticalProperty;

/**
 * Control all political systems
 */
@SetModel(PoliticalSystemModel.class)
public class PoliticalSystemController extends Controller {

	private PoliticalSystemView politicalSystemView;

	@Override
	protected void initialize() {

	}

	@Override
	protected void layoutView() {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		politicalSystemView.setFrame(10.0f, width/2 - (width/2) * 3/5, width/2, height/2 );


	}

	public void show(){
		showView(politicalSystemView);
	}

	public void hide(){
		showView();
	}

	@Override
	protected void tick() {
		// Update the Wallet
		int wallet = (Integer) getModel().get(PoliticalProperty.WALLET.id());
		wallet += (Integer) getModel().get(PoliticalProperty.DELTA_WALLET.id());

		getModel().set(PoliticalProperty.WALLET.id(), wallet);

		// Update the Relations
		double relations = (Double) getModel().get(PoliticalProperty.RELATIONS.id());
		relations += (Double) getModel().get(PoliticalProperty.DELTA_RELATIONS.id());

		getModel().set(PoliticalProperty.RELATIONS.id(), relations);

		// Reset Deltas For this class
		getModel().set(PoliticalProperty.DELTA_RELATIONS.id(), 0.0);
		getModel().set(PoliticalProperty.DELTA_WALLET.id(), 0);
	}

	/**
	 * Set a change to be in effect on next update for a nations emissions
	 * 
	 * @param deltaWallet
	 *            Change being applied int in [-10, 10] - {0}
	 */
	public void setDeltaWallet(int deltaWallet) {
		getModel().set(PoliticalProperty.DELTA_WALLET.id(), deltaWallet);
	}

	/**
	 * Set a change to be in effect next update for a nations public relations
	 * 
	 * @param deltaRelations
	 *            Changeto be applied to relations
	 */
	public void setDeltaRelations(double deltaRelations) {
		getModel().set(PoliticalProperty.DELTA_RELATIONS.id(), deltaRelations);
	}

	/**
	 * Get the Wallet amount
	 */
	public int getWallet() {
		return (Integer) getModel().get(PoliticalProperty.WALLET.id());
	}

	/**
	 * Get the Relations level
	 */
	public double getRelations() {
		return (Double) getModel().get(PoliticalProperty.RELATIONS.id());
	}
}
