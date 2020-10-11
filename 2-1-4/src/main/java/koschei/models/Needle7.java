package koschei.models;

import org.springframework.stereotype.Component;

@Component
public class Needle7 {
    private Death8 death;

    public Needle7(Death8 death) {
        this.death = death;
    }

    @Override
    public String toString() {
        return death.toString();
    }
}
