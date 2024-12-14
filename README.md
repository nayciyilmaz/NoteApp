![Logo](https://github.com/nayciyilmaz/NoteApp/blob/main/proje.png?raw=true)

## Features

Note Creation: Allows users to create a note by adding a title and description.
Save and Clear: Notes can be saved, and the input fields will be cleared after saving.
Delete Notes: Users can delete notes from the list.
Dynamic List Display: Displays a list of saved notes with title, description, and date.
Input Validation: Ensures that only alphabetic characters and spaces are allowed for note title and description.

## Project Structure

NoteScreen(): Main screen that allows users to create, save, and view notes.
NoteCardList(): Displays the list of saved notes with the option to delete them.
NoteViewModel: Contains the logic for managing note data, including saving, updating, and deleting notes.
EditTextField(): A custom component for input fields where users can enter the note title and description.

## Technologies Used

Kotlin: The programming language used for developing the app.
Jetpack Compose: Modern UI toolkit for building the user interface.
StateFlow: Used for managing state changes for the UI components.
Material3: Material Design components for the UI, such as buttons, cards, and icons.
