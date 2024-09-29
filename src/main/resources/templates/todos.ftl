<!DOCTYPE html>
<html>
<head>
<title>Todo List</title>
<style>
.todo-item {
display: flex;
align-items: center;
margin-bottom: 10px;
}
.todo-task {
flex: 1;
margin-right: 10px;
}
.todo-actions form {
display: inline;
margin-left: 5px;
}
.todo-actions input[type="text"] {
width: 150px; /* Explicit width for the input */
margin-right: 5px;
}
    </style>
</head>
<body>
    <h1>Todo List</h1>

    <ul style="list-style-type: none;">
        <#list todos as todo>
            <li class="todo-item">
                <div class="todo-task">
                    ${todo.task} - ${todo.completed?string("Completed", "Pending")}
                </div>
                <div class="todo-actions">
                    <form method="post" action="/todos/complete">
                        <input type="hidden" name="id" value="${todo.id}" />
                        <button type="submit">Complete</button>
                    </form>

                    <form method="post" action="/todos/update">
                        <input type="hidden" name="id" value="${todo.id}" />
                        <input type="text" name="task" value="${todo.task}" required />
                        <button type="submit">Update</button>
                    </form>

                    <form method="post" action="/todos/delete">
                        <input type="hidden" name="id" value="${todo.id}" />
                        <button type="submit" onclick="return confirm('Are you sure you want to delete this task?');">Delete</button>
                    </form>
                </div>
            </li>
        </#list>
    </ul>

    <h2>Add New Task</h2>
    <form method="post" action="/todos">
        <input type="text" name="task" placeholder="New Task" required/>
        <button type="submit">Add Task</button>
    </form>
</body>
</html>
