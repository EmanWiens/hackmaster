package hackmaster.presentation;

import hackmaster.objects.CardClass;
import hackmaster.objects.PlayerClass;

public interface DrawToScreen {
    void DrawCard(CardClass card, int slot);
    void drawPlayerResource(PlayerClass player);
    void drawPlayedCard(CardClass card, boolean delay);
}
