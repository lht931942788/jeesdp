import cn.org.rookie.jeesdp.mapper.annotation.Column
import cn.org.rookie.jeesdp.mapper.annotation.Table

@Table("demo")
class Demo {

    @Column(primary = true)
    String id
    Date time
    String name

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getId() {
        return id
    }

    void setId(String id) {
        this.id = id
    }

    Date getTime() {
        return time
    }

    void setTime(Date time) {
        this.time = time
    }
}
