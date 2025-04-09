package exercizes.chapo6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// 엘리베이터의 상태
sealed class ElevatorState {
    data class WaitingAtFloor(val floor: Int, val doorsOpen: Boolean) : ElevatorState()
    data class Traveling(val fromFloor: Int, val toFloor: Int) : ElevatorState()
}

// 엘리베이터의 커맨드
sealed class ElevatorCommand {
    data class CallElevator(val floor: Int) : ElevatorCommand()
    data class GoToFloor(val floor: Int) : ElevatorCommand()
    object OpenDoors : ElevatorCommand()
    object CloseDoors : ElevatorCommand()
}

fun handleCommand(state: ElevatorState, command: ElevatorCommand): ElevatorState {
    return when (state) {
        is ElevatorState.WaitingAtFloor -> when (command) {
            is ElevatorCommand.CallElevator -> {
                if (state.floor == command.floor) state
                else ElevatorState.Traveling(state.floor, command.floor)
            }
            is ElevatorCommand.GoToFloor -> {
                if (state.floor == command.floor) state
                else ElevatorState.Traveling(state.floor, command.floor)
            }
            ElevatorCommand.OpenDoors -> state.copy(doorsOpen = true)
            ElevatorCommand.CloseDoors -> state.copy(doorsOpen = false)
        }
        is ElevatorState.Traveling -> when (command) {
            is ElevatorCommand.CallElevator -> {
                state
            }
            is ElevatorCommand.GoToFloor -> {
                ElevatorState.Traveling(state.fromFloor, command.floor)
            }
            ElevatorCommand.OpenDoors, ElevatorCommand.CloseDoors -> state
        }
    }
}

class ElevatorStateMachineTest {
    @Test
    fun `엘리베이터 호출 시 Traveling 상태로 전이`() {
        val initialState = ElevatorState.WaitingAtFloor(floor = 1, doorsOpen = false)
        val newState = handleCommand(initialState, ElevatorCommand.CallElevator(floor = 3))
        assertEquals(ElevatorState.Traveling(fromFloor = 1, toFloor = 3), newState)
    }

    @Test
    fun `이미 같은 층에 있을 때 호출 시 상태 유지`() {
        val initialState = ElevatorState.WaitingAtFloor(floor = 2, doorsOpen = false)
        val newState = handleCommand(initialState, ElevatorCommand.CallElevator(floor = 2))
        assertEquals(initialState, newState)
    }

    @Test
    fun `문 열기 요청 시 doorsOpen이 true로 변경`() {
        val initialState = ElevatorState.WaitingAtFloor(floor = 1, doorsOpen = false)
        val newState = handleCommand(initialState, ElevatorCommand.OpenDoors)
        assertEquals(ElevatorState.WaitingAtFloor(floor = 1, doorsOpen = true), newState)
    }

    @Test
    fun `Traveling 중 문 열기 요청은 무시`() {
        val initialState = ElevatorState.Traveling(fromFloor = 1, toFloor = 3)
        val newState = handleCommand(initialState, ElevatorCommand.OpenDoors)
        assertEquals(initialState, newState)
    }

    @Test
    fun `Traveling 중 새 목적지로 변경 가능`() {
        val initialState = ElevatorState.Traveling(fromFloor = 1, toFloor = 3)
        val newState = handleCommand(initialState, ElevatorCommand.GoToFloor(floor = 5))
        assertEquals(ElevatorState.Traveling(fromFloor = 1, toFloor = 5), newState)
    }
}