/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

                 
const serStars = document.querySelectorAll(".Ser i");
const techStars = document.querySelectorAll(".Tech i");
const sAStars = document.querySelectorAll(".SA i");


const ser = document.getElementById("hidden-rateSer");
const tech = document.getElementById("hidden-rateTech");
const sA = document.getElementById("hidden-rateSA");
                    
console.log(serStars);
serStars.forEach((star, index1)=>{
    
    star.addEventListener("click",() =>{
        ser.value = index1+1;
        console.log(ser.value);
        serStars.forEach((star1, index2)=>{
            index1 >= index2 ? star1.classList.add("checked") : star1.classList.remove("checked");
        });
    });    
});

techStars.forEach((star, index1)=>{
    star.addEventListener("click",() =>{
        tech.value = index1+1;
        console.log(tech.value);
        techStars.forEach((star1, index2)=>{
            index1 >= index2 ? star1.classList.add("checked") : star1.classList.remove("checked");
        });
    });    
});

sAStars.forEach((star, index1)=>{
    star.addEventListener("click",() =>{
        sA.value = index1+1;
        console.log(sA.value);
        sAStars.forEach((star1, index2)=>{
            index1 >= index2 ? star1.classList.add("checked") : star1.classList.remove("checked");
        });
    });    
});
                    
            


