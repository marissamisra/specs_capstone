const coursesList = document.querySelector("#all-courses-list");
const studentList = document.querySelector("#all-students-list");
const teacherList = document.querySelector("#all-teachers-list");
const newUserForm = document.querySelector("#new-user");
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
  });
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
  });
}

function addUser(event) {
  event.preventDefault();
  const userName = newUserName.value;
  const isTeacherChecked = isTeacherCheckbox.checked;
  const body = {
    name: userName,
    instructor: isTeacherChecked,
  };
  axios.post("http://localhost:8080/api/users", body).then((res) => {
    console.log(res.data);
    newUserName.value = "";
    isTeacherCheckbox.checked = false;
    getAllUsers();
  });
}

newUserForm.addEventListener("submit", addUser);

getAllCourses();
getAllUsers();
