package au.azzmosphere.physicalobject.perspective;

public class PerspectiveImpl implements Perspective{
    private CardinalDirection cardinalDirection;

    @Override
    public void setDirection(CardinalDirection cardinalDirection) {
        this.cardinalDirection = cardinalDirection;
    }

    @Override
    public CardinalDirection getDirection() {
        return cardinalDirection;
    }
}
