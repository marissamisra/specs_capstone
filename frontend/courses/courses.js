const studentList = document.querySelector("#student-list");

function getAllCourses () {
    axios.get('http://localhost:8080/api/courses').then(res => {
        if(res.data){
            console.log(res.data)
            studentList.innerHTML = res.data.map(element => {
                return `<a href=${`../courses/?course=${element.id}`}><li> ${element.name} </li></a>`
            }).join('')
        }
    });
}

getAllCourses();