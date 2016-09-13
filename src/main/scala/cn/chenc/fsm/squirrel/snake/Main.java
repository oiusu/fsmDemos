package cn.chenc.fsm.squirrel.snake;

import org.squirrelframework.foundation.fsm.*;
import org.squirrelframework.foundation.fsm.StateMachine.TransitionCompleteEvent;
import cn.chenc.fsm.squirrel.snake.SnakeController.SnakeEvent;
import cn.chenc.fsm.squirrel.snake.SnakeController.SnakeState;

public class Main {
    
    public static void main(String[] args) {
        final SnakeModel gameModel = new SnakeModel();
        UntypedStateMachineBuilder builder = StateMachineBuilderFactory.create(SnakeController.class);
        builder.setStateMachineConfiguration(StateMachineConfiguration.create().enableRemoteMonitor(true));
        
        // define timed state
        /**
         * Define a timed state
         * @param stateId state id
         * @param initialDelay initial delay ms
         * @param timeInterval time period if null not repeat
         * @param autoEvent
         * @param autoContext
         * @return timed state
         */
        //MutableState<T, S, E, C> defineTimedState(S stateId, long initialDelay,
        //long timeInterval, E autoEvent, C autoContext);
        builder.defineTimedState(SnakeState.UP, 0, GameConfigure.FRAME_TIME, SnakeEvent.MOVE_AHEAD, gameModel);
        builder.defineTimedState(SnakeState.DOWN, 0, GameConfigure.FRAME_TIME, SnakeEvent.MOVE_AHEAD, gameModel);
        builder.defineTimedState(SnakeState.RIGHT, 0, GameConfigure.FRAME_TIME, SnakeEvent.MOVE_AHEAD, gameModel);
        builder.defineTimedState(SnakeState.LEFT, 0, GameConfigure.FRAME_TIME, SnakeEvent.MOVE_AHEAD, gameModel);
        
        SnakeController controller = builder.newUntypedStateMachine(SnakeState.NEW);
        final SnakeGame game = new SnakeGame(controller, gameModel);
        controller.addTransitionCompleteListener(new StateMachine.TransitionCompleteListener<UntypedStateMachine, Object, Object, Object>() {
            @Override
            public void transitionComplete(TransitionCompleteEvent<UntypedStateMachine, Object, Object, Object> event) {
                game.repaint();
                game.setTitle("Greedy Snake("+gameModel.length()+"): "+
                        event.getSourceState()+"--["+event.getCause()+"]--"+event.getTargetState());
            }
        });
        controller.export();
        game.startGame();
    }
}
