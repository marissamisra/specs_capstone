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

async function populateUserSelectDropDown () {

    const allUsersResponse = await getAllUsers()
    const allUsers = allUsersResponse.data
    const selectMessageObj = {
        id: null,
        name: '--Please choose an option--',
        instructor: false
    }
    allUsers.unshift(selectMessageObj)
    console.log({allUsers})
    console.log({enrolledUsers})

    const filteredUsers = allUsers.filter(obj1 => {
        return !enrolledUsers.some(obj2 => obj2.id === obj1.id);
    });

    console.log(filteredUsers)

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
    console.log(userSelect.value)
    if (userSelect.value) {
        const body = +userSelect.value
        axios.post(`http://localhost:8080/api/courses/${courseId}/users`, [body]).then(res => {
            console.log(res.data)
            getAllCourses().then(() => {
                populateUserSelectDropDown();
            })
        })
    }
}

function dropStudentFromCourse (studentId) {
    
}

async function getAllUsers() {
    return await axios.get("http://localhost:8080/api/users")
}

form.addEventListener('submit', addUserToCourse)


getAllCourses().then(() => {
    populateUserSelectDropDown();
});