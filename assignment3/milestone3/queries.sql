#1
select Avg(count_review) from 
(select cast(review.timestamp as date) as dt, count(*) as count_review
from
review 
group by cast(review.timestamp as date)) as count_vt;

#2
select review.* from `review`  
group by Restaurant_idRestaurant having count(review.idreview) >=30 order by count(review.idreview) asc;

#3
select SUM(upVote),userid from 
(select COUNT(*) as upVote , review.`idreview` as reviewid,review.`User_idUser` as userid 
	from user_upvotes_review join  review on review.idreview = user_upvotes_review.`review_idreview` 
	group by user_upvotes_review.`review_idreview`) 
as countReview group by userid order by SUM(upVote) LIMIT 1;

#4
select avg(count_review) from (select count(*) as count_review from review 
group by review.User_idUser) as count;

#5
select sum(order.price) from order;

#6
select sum(`order`.price) as totPrice , `order`.User_idUser as userid from `order` group by `order`.User_idUser order by sum(`order`.price) LIMIT 1;

#7
set user.isActive = 0 where user.idUser = ?;

#8
insert into `order`(`payment_status` ,`delivery_status` , `price` ,`Restaurant_idRestaurant` ,`Location_idLocation` ,`rating` ,`feedback` ,`timestamp`) values(?,?,?,?,?,?,?,?,?)
insert into `order_has_item`(`order_idOrder`,`item_iditem`,`quantity`) values(?,?,?)

