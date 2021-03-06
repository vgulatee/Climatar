package game.climatar.systems.ghg;

import java.util.HashMap;

import com.badlogic.gdx.math.MathUtils;

import game.climatar.architecture.Controller;
import game.climatar.map.Nation;
import game.climatar.systems.ghg.GHGSystemModel.GHGProperty;

/**
 * GHG controller which controls the controllers for the air/earth/water/fire
 * nations
 */

public class GHGController extends Controller {

	// constants
	private static final int INITIAL_VALUE_FN = 50;
	private static final int INITIAL_VALUE_EN = 35;
	private static final int INITIAL_VALUE_WN = 25;
	private static final int INITIAL_VALUE_AN = 10;

	// child controllers
	GHGSystemController earthNationController;
	GHGSystemController fireNationController;
	GHGSystemController waterNationController;
	GHGSystemController airNationController;

	private HashMap<Nation, GHGSystemController> ghgSystems = new HashMap<Nation, GHGSystemController>();

	@Override
	protected void initialize() {
		((GHGSystemModel) earthNationController.getModel()).init(Nation.EARTH, INITIAL_VALUE_EN);
		((GHGSystemModel) fireNationController.getModel()).init(Nation.FIRE, INITIAL_VALUE_FN);
		((GHGSystemModel) airNationController.getModel()).init(Nation.AIR, INITIAL_VALUE_AN);
		((GHGSystemModel) waterNationController.getModel()).init(Nation.WATER, INITIAL_VALUE_WN);

		//Setup Map
		ghgSystems.put(Nation.FIRE, fireNationController);
		ghgSystems.put(Nation.EARTH, earthNationController);
		ghgSystems.put(Nation.AIR, airNationController);
		ghgSystems.put(Nation.WATER, waterNationController);
		
		getModel().set(GHGProperty.TOTAL_EMISSIONS_PER_UPDATE.id(), 0.0);
	}

	@Override
	protected void layoutView() {
		
	}

	@Override
	protected void tick() {
		// set the model's total emissions (by all nations)
		for(Nation n : Nation.values()) {
			setNationGHGDelta(n, MathUtils.random(-1, 1));
		}
	}

	public int gettotalEmmisions(){
		int totalEmissionsPerUpdate = 0;

		totalEmissionsPerUpdate += earthNationController.getEmissionPerUpdate();
		totalEmissionsPerUpdate += fireNationController.getEmissionPerUpdate();
		totalEmissionsPerUpdate += airNationController.getEmissionPerUpdate();
		totalEmissionsPerUpdate += waterNationController.getEmissionPerUpdate();
		return totalEmissionsPerUpdate;
	}

	public void setNationGHGDelta(Nation n, int delta){
		if(isActive())
			ghgSystems.get(n).setDeltaEmissions(delta);
	}

	public double getEmissionsPerUpdate(){
		return (Double) getModel().get(GHGProperty.TOTAL_EMISSIONS_PER_UPDATE.id());
	}

	public GHGSystemController getGHGSystemController(Nation n) {
		return ghgSystems.get(n);
	}
}
