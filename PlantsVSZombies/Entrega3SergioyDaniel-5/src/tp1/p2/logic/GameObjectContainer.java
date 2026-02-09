package tp1.p2.logic;

import java.util.ArrayList;
import java.util.List;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.view.Messages;

public class GameObjectContainer {

	private List<GameObject> gameObjects; 
	

	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
	}
	
	public void addGameObject(GameObject obj) {
		gameObjects.add(obj);
	}
	
	public String drawObject(int col, int row) {
		StringBuilder buffer = new StringBuilder();
		boolean sunPainted = false;
		boolean sunAboutToPaint = false;

		for (GameObject g : gameObjects) {
			if(g.isInPosition(col, row)) {
				String objectText = g.toString();
				sunAboutToPaint = objectText.indexOf(Messages.SUN_SYMBOL) >= 0;
				if (sunAboutToPaint) {
					if (!sunPainted) {
						buffer.append(objectText);
						sunPainted = true;
					}
				} else {
					buffer.append(objectText);
				}
			}
		}
		return buffer.toString();
	}
	
	public void update(GameWorld game) {
		int cont = 0;
		for (GameObject object : gameObjects) {
			if(!object.update()) {
				cont++;
			}
		}
		for(int i = 0; i < cont; ++i) {
			game.addSun();
		}
	}
	
    public boolean eliminarMuertos() {
    	boolean dev = false;
		List<GameObject> vivos = new ArrayList<>();
		for (GameObject object : gameObjects) {
			if (object.isAlive()) vivos.add(object);
			else dev = true;
		}
		gameObjects = vivos;
		return dev;
	}
	
	
	public List<GameItem> getObjectInPosition (int col, int row) {
		List<GameItem> o = new ArrayList<>();
		for (GameObject object : gameObjects) {
			if (object.isInPosition(col, row)) o.add(object);
		}
		return o;
	}
	
	public List<GameItem> getLimits(int col, int row) {
		List<GameItem> limits = new ArrayList<>();
		for (int i = col - 1; i < col + 2; ++i) {
			List<GameItem> list1 = getObjectInPosition(i, row - 1);
			List<GameItem> list2 = getObjectInPosition(i, row + 1);
			List<GameItem> list3 = getObjectInPosition(i, row);
			
			for (GameItem item : list1) {
				limits.add(item);
			}
			
			for (GameItem item : list2) {
				limits.add(item);
			}
			
			for (GameItem item : list3) {
				limits.add(item);
			}	
		}
		
		return limits;
	}
	
	public boolean catchCoins(int col, int row) {
		
		boolean dev = false;
		
		List<GameItem> list = getObjectInPosition(col, row);
		for (GameItem item : list) {
			if (item.catchSun()) dev = true;
		}
		
		return dev;
	}

}
