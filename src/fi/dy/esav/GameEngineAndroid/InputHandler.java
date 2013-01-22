package fi.dy.esav.GameEngineAndroid;


public class InputHandler{

	private InputState inputstate;
	private GameEngine engine;
	
	/**
	 * Disabled default constructor
	 */
	@SuppressWarnings("unused")
	private InputHandler() {}
	
	/**
	 * Constructor
	 * @param state The InputState to hold the inputs states
	 */
	public InputHandler(GameEngine engine, InputState state) {
		this.inputstate = state;
		this.engine = engine;
	}
	
}
