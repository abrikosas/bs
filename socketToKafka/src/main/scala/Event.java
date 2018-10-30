import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;
import org.xml.sax.SAXException;


@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Event {

    @SerializedName("events")
    private List<Event> mEvents;
    @SerializedName("gameId")
    private Long mGameId;
    @SerializedName("name")
    private String mName;
    @SerializedName("x")
    private Long mX;
    @SerializedName("y")
    private Long mY;

    public List<Event> getEvents() {
        return mEvents;
    }

    public void setEvents(List<Event> events) {
        mEvents = events;
    }

    public Long getGameId() {
        return mGameId;
    }

    public void setGameId(Long gameId) {
        mGameId = gameId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Long getX() {
        return mX;
    }

    public void setX(Long x) {
        mX = x;
    }

    public Long getY() {
        return mY;
    }

    public void setY(Long y) {
        mY = y;
    }

}
