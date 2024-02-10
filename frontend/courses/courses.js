const courseName = document.querySelector("#course");
const students = document.querySelector("#students");
const instructor = document.querySelector("#instructors");
const userSelect = document.querySelector("#add-existing-user")
const form = document.querySelector('#add-user-to-course')

const searchParams = new URLSearchParams(window.location.search)
const courseId = searchParams.get('course')

let enrolledUsers = []

function getAllCourses () {
    return new Promise((resolve, reject) => {
        axios.get(`http://localhost:8080/api/courses/${courseId}`).then(res => {
            if(res.data){
                courseName.innerHTML = res.data.name
                listStudents(res.data.enrolledUsers)
                listInstructors(res.data.enrolledUsers)
                resolve()
            } 
        });
    })
}

function listStudents (userList) {
    enrolledUsers = userList
    if (userList.length) {
        students.innerHTML = userList.map(element => {
            if (!element.instructor) {
                return `<a href=${`../students/?student=${element.id}&course=${courseId}`}><li>${element.name}</li></a> <button onclick=dropStudentFromCourse(${element.id})>Drop</button>`
            }
        }).join('');
    }
}

function listInstructors (userList) {
    enrolledUsers = userList
    if (userList.length) {
        instructor.innerHTML = userList.map(element => {
            if (element.instructor) {
                return `<a href=${`../students/?student=${element.id}&course=${courseId}`}><li>${element.name}</li></a> <button onclick=dropStudentFromCourse(${element.id})>Drop</button>`
            }
        }).join('');
    }
}

async function populateUserSelectDropDown () {

    const allUsersResponse = await getAllUsers()
    const allUsers = allUsersResponse.data
    const selectMessageObj = {
        id: null,
        name: '--Please choose an option--',
        instructor: false
    }
    allUsers.unshift(selectMessageObj)
    const filteredUsers = allUsers.filter(obj1 => {
        return !enrolledUsers.some(obj2 => obj2.id === obj1.id);
    });
    userSelect.innerHTML = ''
    filteredUsers.forEach(user => {
        const option = document.createElement('option');
        option.value = user.id;
        option.textContent = user.name;
        userSelect.appendChild(option);
    });
}

function addUserToCourse(event) {
    event.preventDefault()
    if (userSelect.value) {
        const body = +userSelect.value
        axios.post(`http://localhost:8080/api/courses/${courseId}/users`, [body]).then(res => {
            repopulateAll()
        })
    }
}

function dropStudentFromCourse (studentId) {
    axios.delete(`http://localhost:8080/api/courses/${courseId}/users`, {data: [studentId]}).then(() => {
        repopulateAll()
    })
}

async function getAllUsers() {
    return await axios.get("http://localhost:8080/api/users")
}

function repopulateAll () {
    getAllCourses().then(() => {
        populateUserSelectDropDown();
    });
}

form.addEventListener('submit', addUserToCourse)

repopulateAll()
