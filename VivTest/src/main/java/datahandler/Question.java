
package datahandler;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "Question",
    "Answer"
})
public class Question {

    @JsonProperty("type")
    private String type;
    @JsonProperty("Question")
    private String question;
    @JsonProperty("Answer")
    private String answer;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("Question")
    public String getQuestion() {
        return question;
    }

    @JsonProperty("Question")
    public void setQuestion(String question) {
        this.question = question;
    }

    @JsonProperty("Answer")
    public String getAnswer() {
        return answer;
    }

    @JsonProperty("Answer")
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
