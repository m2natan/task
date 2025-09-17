# Task Tracker

Build a CLI app to track your tasks and manage your to-do list.
[Task Tracker](https://roadmap.sh/projects/task-tracker)
## Usage

```bash
mvn clean package
```

Adding a new task
```bash
java -jar target/tracker-1.0-SNAPSHOT.jar  add "Buy groceries"
```
### Output: Task added successfully (ID: 1)


Updating and deleting tasks
```bash
java -jar target/tracker-1.0-SNAPSHOT.jar  update 1 "Buy groceries and cook dinner"
```

```bash
java -jar target/tracker-1.0-SNAPSHOT.jar  delete 1
```

Marking a task as in progress or done
```bash
java -jar target/tracker-1.0-SNAPSHOT.jar  mark-in-progress 1
```

```bash
java -jar target/tracker-1.0-SNAPSHOT.jar  mark-done 1
```

Listing all tasks
```bash
java -jar target/tracker-1.0-SNAPSHOT.jar  list
```

Listing tasks by status
```bash
java -jar target/tracker-1.0-SNAPSHOT.jar  list done
```

```bash
java -jar target/tracker-1.0-SNAPSHOT.jar  list todo
```

```bash
java -jar target/tracker-1.0-SNAPSHOT.jar  list in-progress
```
