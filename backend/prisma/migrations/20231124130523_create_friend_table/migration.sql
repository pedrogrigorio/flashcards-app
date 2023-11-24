-- CreateTable
CREATE TABLE "Friend" (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "username" TEXT NOT NULL,
    "name" TEXT NOT NULL,
    "imgSrc" TEXT NOT NULL DEFAULT '',
    "dayStreak" INTEGER NOT NULL,
    "cardsReviewed" INTEGER NOT NULL,
    "userId" INTEGER,
    CONSTRAINT "Friend_userId_fkey" FOREIGN KEY ("userId") REFERENCES "User" ("id") ON DELETE SET NULL ON UPDATE CASCADE
);

-- CreateIndex
CREATE UNIQUE INDEX "Friend_username_key" ON "Friend"("username");
