package Controller;
import java.util.ArrayList;

import View.BaseView;

/**
 * This class helps to manage the flow of all the View object in the application.
 * This class manages the transitioning of one View to another View, or transitioning from one BaseView to the previous BaseView
 */

public class ViewsManager {

	/**
	 * This is the ArrayList of views, which is imagined to be our views stack
	 * It contains all the views that are currently in the view stack
	 */
	private static ArrayList<BaseView> views = new ArrayList<BaseView>();


	/**
	 * Constructor is made private because this class is only used for the purpose of managing the Views via the static methods and the static variable.
	 * Hence it is not supposed to be instantiated.
	 */
	private ViewsManager() {}


	/**
	 * This method helps to push new BaseView onto the existing BaseView stack.
	 * Upon doing so, this new BaseView on top of the stack is then activated to display its content
	 * @param baseView The BaseView object to be pushed onto the views stack
	 */
	public static void pushView(BaseView baseView) {
		views.add(baseView);
		baseView.activate();
	}

	/**
	 * This method helps to pop the current View object that is on top of the stack
	 * Upon doing so, the next View object that is now at the top of the views stack will be activated to show its content.
	 * If the views stack only contains one View object, it will not be popped
	 */
	public static void popView() {
		if (views.size() > 1) {
			views.remove(views.size() - 1);
			views.get(views.size() - 1).activate();
		}else {
			System.out.println("\n\nViews stack is empty....\n\n");
			views.get(views.size() - 1).activate();
		}
	}

	/**
	 * This method helps to pop multiple View objects in the views stack at once.
	 * The parameter count specifies the number of View objects to be popped from the views stack
	 * @param count The number of View object to be popped from the stack at once
	 */
	public static void popViews(int count) {
		for (int i = 0 ; i < count ; i++) {
			if (views.size() > 1) {
				views.remove(views.size() - 1);
			}
		}
		views.get(views.size() - 1).activate();
	}



	/**
	 * This method will continuously pop View objects from the views stack until it founds a BaseView object at the top of the stack that is of the specified class type.
	 *
	 * @param viewClass The View Class that will stop the popping of views
	 */
	public static void popTillView(Class<?> viewClass){
		while (true) {
			if (views.size() > 1) {
				Class currentViewClass = views.get(views.size() - 1).getClass();
				if (!(viewClass.equals(currentViewClass))){
					views.remove(views.size() - 1);
				}else{
					break;
				}
			}else{break;}
		}
		views.get(views.size() - 1).activate();
	}

	/**
	 * This method will keep popping View object from the views stack until the views stack only contains one BaseView object.
	 */
	public static void popToBaseView(){
		for (int x = views.size() - 1 ; x > 0 ; x--){
			views.remove(x);
		}
		views.get(0).activate();
	}
	
}