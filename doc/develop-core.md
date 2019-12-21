# develop highlights

## dao（数据库层）
- 项目中运用 JPA 作为 DAO 层主要框架
- 主要原因：比 MyBatis 约束强点，上手没那么简单
### 自动建表相关优化 [附1](#appendix01)

## user（用户模块）
- 开发中...


---
### appendix01
> 实现方式比较简单,详参具体类注释

#### 表中字段顺序 和 实体类中字段顺序一致
  - [自定义 PropertyContainer](../../mall-common/src/main/java/org/hibernate/cfg/PropertyContainer.java)

#### 调整建表后父类字段顺序（默认父类字段优先）
> 实体类继承了父类，又不想父类字段都在表字段最前边
>
> 例如 id 放在第一位，createTime, deleteFlag 放最后
  - [自定义 InheritanceState](../../mall-common/src/main/java/org/hibernate/cfg/InheritanceState.java)
