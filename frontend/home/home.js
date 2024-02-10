const coursesList = document.querySelector("#all-courses-list");
const studentList = document.querySelector("#all-students-list");
const teacherList = document.querySelector("#all-teachers-list");
const newCourseForm = document.querySelector('#new-course');
const newUserForm = document.querySelector("#new-user");
const newCourseName = document.querySelector("#new-course-name");
const newUserName = document.querySelector("#new-user-name");
const isTeacherCheckbox = document.querySelector("#isTeacher");

function getAllCourses() {
  axios.get("http://localhost:8080/api/courses").then((res) => {
    if (res.data) {
      coursesList.innerHTML = res.data
        .map((element) => {
          return `<a href=${`../courses/?course=${element.id}`}><li> ${
            element.name
          } </li></a>`;
        })
        .join("");
    }
  }).catch((err) => console.log(err));
}

function getAllUsers() {
  axios.get("http://localhost:8080/api/users").then((res) => {
    if (res.data) {
      teacherList.innerHTML = res.data
        .map((element) => {
          if (element.instructor) {
            return `<li>${element.name}</li>`;
          }
        })
        .join("");
      studentList.innerHTML = res.data
        .map((element) => {
          if (!element.instructor) {
            return `<li>${element.name}</li>`;
          }
        })
        .join("");
    }
  }).catch((err) => console.log(err));
}

function addUser(event) {
  event.preventDefault();
  const userName = newUserName.value;
  const isTeacherChecked = isTeacherCheckbox.checked;
  const body = {
    name: userName,
    instructor: isTeacherChecked,
  };
  axios.post("http://localhost:8080/api/users", body).then(() => {
    newUserName.value = "";
    isTeacherCheckbox.checked = false;
    getAllUsers();
  }).catch((err) => console.log(err));
}

function addCourse (event) {
  event.preventDefault();
  const courseName = newCourseName.value
  const body = {
    name: courseName
  }
  axios.post("http://localhost:8080/api/courses", body).then(() => {
    newCourseName.value = ""
    getAllCourses();

  }).catch((err) => console.log(err))
}

newUserForm.addEventListener("submit", addUser);
newCourseForm.addEventListener("submit", addCourse);

getAllCourses();
getAllUsers();
