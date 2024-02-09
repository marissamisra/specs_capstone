const courseName = document.querySelector("#course-name");
const studentName = document.querySelector("#student");
const attendanceList = document.querySelector("#attendance");

const searchParams = new URLSearchParams(window.location.search)
const courseId = searchParams.get('course')
const studentId = searchParams.get('student')

function getStudentAttendance () {
    axios.get(`http://localhost:8080/api/attendance/student/${studentId}`).then(res => {
        if(res.data) {
            attendanceList.innerHTML = res.data.map(element => {
                return `
                Date: ${element.date} <br>
                Present: ${element.present} <br>
                Tardy: ${element.tardy} <br>
                <br>
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
        // console.log(res.data)
        if(res.data){
            courseName.innerHTML = res.data.name
        }
    });
}

getStudentAttendance();
getStudentName();
getCourseName();