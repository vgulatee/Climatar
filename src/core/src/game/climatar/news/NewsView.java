package game.climatar.news;

import java.util.List;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import game.climatar.architecture.AllowController;
import game.climatar.architecture.Model;
import game.climatar.architecture.View;

/**
 * Created by vishe on 2017-04-06.
 */

@AllowController(NewsEventControl.class)
public class NewsView extends View {
    // Components
    private VisTable table;
    private VisLabel eventMessageLabel;
    private VisTextButton okayButton, noButton;

    private boolean isBuilt = false;


    @Override
    public void dispose() {

    }

    @Override
    public void build(Group group) {
        table = new VisTable();
        okayButton = new VisTextButton("Okay");
        noButton = new VisTextButton("No");

        eventMessageLabel = new VisLabel("dsa");
        //Add Events
        okayButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                responceOkay();
            }
        });

        noButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                responceNo();
            }
        });

        //TODO REMOVE BELOW
        //Layout
        Value widthVal = new Value() {
            @Override
            public float get(Actor context) {
                return getFrame().width;
            }
        };

        Value heightVal = new Value() {
            @Override
            public float get(Actor context) {
                return getFrame().height/8;
            }
        };

        table.add(eventMessageLabel).maxWidth(widthVal).height(heightVal).align(Align.center);
        table.row();
        table.add(okayButton);
        table.add(noButton);

        group.addActor(table);
        //TODO UNTIL HERE
        isBuilt = true;
    }

    @Override
    public void layout(float x, float y, float width, float height) {
        table.pack();
        table.invalidate();
        table.validate();
        table.layout();
    }


    public void showNewsEvent(NewsEvent event) {

    }

    @Override
    public void update(Model model) {
        if(!isBuilt || ((NewsEventControl) getController()).currentEvent == null) {
            //System.out.print("FUCK");
            return;
        }

        NewsEvent currentEvent = ((NewsEventControl) getController()).currentEvent;
        eventMessageLabel.setText(currentEvent.getDescription());
        
//        table.clear();


        Pixmap p = new Pixmap(50, 50, Pixmap.Format.RGBA8888);
        p.setColor(1/255,1/255,1/255,0.3f);
        p.fill();

        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(p))));
//        VisLabel label = new VisLabel(currentEvent.getDescription());
//        label.setWrap(true);
//        label.setWidth(100); // or even as low as 10
//        table.add(label).width(getFrame().getWidth());// <--- here you define the width! not ^
//        table.row();
        //newtable.pad(5,5,5,5);
//        table.add(okayButton);
//        table.add(noButton);

//        if(currentEvent.getType().equals(NewsEvent.NewsType.ACTIVE))
//            noButton.setVisible(true);
//        else
//            noButton.setVisible(false);


    }

    private void responceOkay(){
            ((NewsEventControl) getController()).respondActiveYes();
    }

    private void responceNo(){
        ((NewsEventControl) getController()).respondActiveNo();
    }

}
