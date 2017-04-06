package game.climatar.map;

import java.util.Arrays;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.kotcrab.vis.ui.widget.VisTable;

import game.climatar.architecture.AllowController;
import game.climatar.architecture.Model;
import game.climatar.architecture.View;
import game.climatar.play.PlayController;

@AllowController({PlayController.class})
public class MapView extends View {

	private DrawableMap map;
	
	private VisTable table;
	
	@Override
	public void build(Group group) {
		int[][] mapSpec = MidpointDisplacement.getMap(4, 2, 2, 2);
		for(int[] arr : mapSpec) {
			System.out.println(Arrays.toString(arr));
		}
		map = new DrawableMap(mapSpec);
		
		table = new VisTable();
		table.add(map);
		
		group.addActor(table);
	}

	@Override
	public void layout(float x, float y, float width, float height) {
		table.pack();
		table.invalidate();
		table.validate();
		table.layout();
	}

	@Override
	public void update(Model model) {
		
	}

	@Override
	public void dispose() {
		map.dispose();
	}
	
}