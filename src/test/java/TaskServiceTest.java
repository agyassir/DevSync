import com.example.decs.Entity.Enums.Status;
import com.example.decs.Entity.Task;
import com.example.decs.Entity.User;
import com.example.decs.Repository.TaskRepository;
import com.example.decs.Service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepo;

    @InjectMocks
    private TaskService taskService;

    private Task task;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        task = new Task();
        task.setId(1L);
        task.setTitle("Sample Task");
        task.setStatus(Status.EN_ATTENTE);

        user = new User();
        user.setId(1L);
        user.setUsername("sampleuser");
    }

    @Test
    void testCreateTask() {
        when(taskRepo.save(any(Task.class))).thenReturn(task);

        Task createdTask = taskService.createTask(task);

        assertNotNull(createdTask);
        assertEquals(task.getTitle(), createdTask.getTitle());
        verify(taskRepo, times(1)).save(task);
    }

    @Test
    void testUpdateTask_Success() {
        Task updatedTask = new Task();
        updatedTask.setTitle("Updated Task");
        updatedTask.setDescription("Updated Description");
        updatedTask.setStatus(Status.TERMINE);

        when(taskRepo.getTaskbyId(1L)).thenReturn(task);
        when(taskRepo.update(any(Task.class))).thenReturn(updatedTask);

        Task result = taskService.updateTask(updatedTask, 1L);

        assertEquals("Updated Task", result.getTitle());
        assertEquals("Updated Description", result.getDescription());
        assertEquals(Status.TERMINE, result.getStatus());
        verify(taskRepo, times(1)).getTaskbyId(1L);
        verify(taskRepo, times(1)).update(any(Task.class));
    }

    @Test
    void testUpdateTask_TaskNotFound() {
        when(taskRepo.getTaskbyId(1L)).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            taskService.updateTask(task, 1L);
        });

        assertEquals("Task with ID 1 not found.", exception.getMessage());
        verify(taskRepo, times(1)).getTaskbyId(1L);
        verify(taskRepo, never()).update(any(Task.class));
    }

    @Test
    void testFindByUser() {
        when(taskRepo.findbyUser(user)).thenReturn(Arrays.asList(task));

        List<Task> tasks = taskService.findByUser(user);

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        verify(taskRepo, times(1)).findbyUser(user);
    }

    @Test
    void testAssignTo_Success() {
        // Do nothing when taskRepo.assignTo(user, task) is called
        doNothing().when(taskRepo).assignTo(user, task);

        boolean result = taskService.assignto(user, task);

        assertTrue(result);
        verify(taskRepo, times(1)).assignTo(user, task);
    }

    @Test
    void testAssignTo_Failure() {
        doThrow(new RuntimeException("Error assigning task")).when(taskRepo).assignTo(user, task);

        boolean result = taskService.assignto(user, task);

        assertFalse(result);
        verify(taskRepo, times(1)).assignTo(user, task);
    }

    @Test
    void testDeleteTask() {
        doNothing().when(taskRepo).delete(1L);

        taskService.deleteTask(1L);

        verify(taskRepo, times(1)).delete(1L);
    }


}


