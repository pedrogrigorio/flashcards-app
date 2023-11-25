-- RedefineTables
PRAGMA foreign_keys=OFF;
CREATE TABLE "new_Friend" (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "username" TEXT NOT NULL,
    "name" TEXT NOT NULL,
    "imgSrc" TEXT NOT NULL DEFAULT '',
    "dayStreak" INTEGER NOT NULL,
    "cardsReviewed" INTEGER NOT NULL,
    "friendOfId" INTEGER,
    "userId" INTEGER,
    CONSTRAINT "Friend_friendOfId_fkey" FOREIGN KEY ("friendOfId") REFERENCES "User" ("id") ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT "Friend_userId_fkey" FOREIGN KEY ("userId") REFERENCES "User" ("id") ON DELETE SET NULL ON UPDATE CASCADE
);
INSERT INTO "new_Friend" ("cardsReviewed", "dayStreak", "id", "imgSrc", "name", "userId", "username") SELECT "cardsReviewed", "dayStreak", "id", "imgSrc", "name", "userId", "username" FROM "Friend";
DROP TABLE "Friend";
ALTER TABLE "new_Friend" RENAME TO "Friend";
CREATE UNIQUE INDEX "Friend_username_key" ON "Friend"("username");
PRAGMA foreign_key_check;
PRAGMA foreign_keys=ON;
