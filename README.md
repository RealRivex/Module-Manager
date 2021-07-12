This is a ModManager for MCP-Reborn-Clients.

Eric Golde coded it in his YouTube Tutorial-Series for 1.8, 
and i made it compatible with 1.16.5 (and eventually all 
other 1.16.x, but not tested at all!)

Full Credit goes to Eric, as the System itself is made by him,
not by me. I only updated it.

Included is the HudManager stuff, the ModRender-Stuff, and 2 Examplemods, FPS and coords.

NOTE: The RenderUtils-Class is used to render the Rectangles
and Horizontal/Vertical Lines, as Minecraft iself has no methods for that anymore.
Please make sure to include it!

ANOTHER NOTE: Because it registers some stuff inside minecraft-code, you really need to watch Eric's video alongside adding this.
I cannot legally provide you the Minecraft-Code without getting jailed lol.


EDIT: thanks to a user, i saw that i forgot that i use 2 methods from a Minecraft internal-class, that i created myself. 
so you need to go into your class `AbstractGui`, and add those two methods:

```
    public static void fill_double(MatrixStack matrixStack, double minX, double minY, double maxX, double maxY, int color) {
        fill(matrixStack.getLast().getMatrix(), minX, minY, maxX, maxY, color);
    }

    private static void fill(Matrix4f matrix, double minX, double minY, double maxX, double maxY, int color) {
        if (minX < maxX) {
            double d = minX;
            minX = maxX;
            maxX = d;
        }

        if (minY < maxY) {
            double d = minY;
            minY = maxY;
            maxY = d;
        }

        float f3 = (float) (color >> 24 & 255) / 255.0F;
        float f = (float) (color >> 16 & 255) / 255.0F;
        float f1 = (float) (color >> 8 & 255) / 255.0F;
        float f2 = (float) (color & 255) / 255.0F;
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(matrix, (float) minX, (float) maxY, 0.0F).color(f, f1, f2, f3).endVertex();
        bufferbuilder.pos(matrix, (float) maxX, (float) maxY, 0.0F).color(f, f1, f2, f3).endVertex();
        bufferbuilder.pos(matrix, (float) maxX, (float) minY, 0.0F).color(f, f1, f2, f3).endVertex();
        bufferbuilder.pos(matrix, (float) minX, (float) minY, 0.0F).color(f, f1, f2, f3).endVertex();
        bufferbuilder.finishDrawing();
        WorldVertexBufferUploader.draw(bufferbuilder);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }
```
