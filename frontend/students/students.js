const courseName = document.querySelector("#course-name");
const studentName = document.querySelector("#student");
const attendanceList = document.querySelector("#attendance");
const attendanceForm = document.querySelector("#add-student-attendance-record")
const isPresentCheckbox = document.querySelector("#present");
const isTardyCheckbox = document.querySelector("#late");
const dateSelector = document.querySelector('#date')

const searchParams = new URLSearchParams(window.location.search)
const courseId = searchParams.get('course')
const studentId = searchParams.get('student')

function getStudentAttendance () {
    axios.get(`http://localhost:8080/api/attendance/student/${studentId}`).then(res => {
        if(res.data) {
            attendanceList.innerHTML = res.data.map(element => {
                return `
                <div class=attendance-unit>
                    Date: ${element.date} <br>
                    Present: ${element.present} <br>
                    Tardy: ${element.tardy} <br>
                    <br>
                </div>
                `
            }).join('')
            attendanceList.innerHTML
        }
    })
}

function getStudentName () {
    axios.get(`http://localhost:8080/api/courses/${courseId}`).then(res => {
        if (res.data) {
            const [student] = res.data.enrolledUsers.filter(element => element.id === +studentId);
            studentName.innerHTML = student.name;
        }
    })
}

function getCourseName () {
    axios.get(`http://localhost:8080/api/courses/${courseId}`).then(res => {
        if(res.data){
            courseName.innerHTML = res.data.name
        }
    });
}

function submitAttendance (event) {
    event.preventDefault();
    
    const body = {
        date: dateSelector.value,
        present: isPresentCheckbox.checked,
        tardy: isTardyCheckbox.checked,
        userId: studentId,
        courseId: courseId
    }
    axios.post('http://localhost:8080/api/attendance', body).then(() => {
        getStudentAttendance()
    })
}

attendanceForm.addEventListener('submit', submitAttendance)

getStudentAttendance();
getStudentName();
getCourseName();