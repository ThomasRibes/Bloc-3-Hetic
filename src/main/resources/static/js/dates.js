// Constraint front input dates can't be a past date
let now = new Date();
let today = now.toISOString().split('T')[0];
let tomorrow = new Date(now.setDate(now.getDate()+1)).toISOString().split('T')[0];
document.getElementsByName("startDate")[0].setAttribute('min', today);
document.getElementsByName("endDate")[0].setAttribute('min', tomorrow);