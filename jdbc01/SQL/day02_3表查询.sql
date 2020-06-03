/*查询马云所对应的权限*/

/*1.将用户表与用户角色管理表进行连接查询*/
select u.name,r.popedomcode
from user_role a join users u
on a.userId = u.userId and u.name='马云'
join role r on r.roleId = a.roleId
