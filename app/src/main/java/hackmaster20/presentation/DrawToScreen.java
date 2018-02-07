package hackmaster20.presentation;

import hackmaster20.objects.CardClass;
import hackmaster20.objects.PlayerClass;

/**
 * Created by Owner on 1/31/2018.
 */

public interface DrawToScreen {
    void DrawCard(CardClass card, int slot);
    void drawPlayerResource(PlayerClass player);
}
