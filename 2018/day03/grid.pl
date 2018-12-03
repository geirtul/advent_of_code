#!/usr/bin/perl
use strict;
use warnings;

use Data::Dumper;
use List::Util "any";

my $file = "input.txt";
open my $input, '<', $file or die "can't open $file: $!";
chomp(my @lines = <$input>);
close $input or die "can't close $file: $!";

my $s1 = "#1 @ 1,3: 4x4";
my $s2 = "#2 @ 3,1: 4x4";
my $s3 = "#3 @ 5,5: 2x2";
my @test = ($s1, $s2, $s3);

# =========== Solve the puzzle ============
# Part 1:
my %overlap;
foreach my $line (@lines) {
  my ($id, $x, $y) = &get_claim_coords($line);
  for my $i (@$x) {
    for my $j (@$y) {
      $overlap{"$i,$j"}++;
    }
  }
}
my $count = 0;
for my $val (values(%overlap)) {
  if ($val >= 2) {
    $count++;
  }
}
print "Number of overlapping square inches: ".$count;

# =========== Subroutine declarations ============


sub get_claim_coords {
  # Splits claim string into useful info
  my $in = shift;
  my @data = $in =~/#(\d+)\s@\s(\d+),(\d+):\s(\d+)x(\d+)/;
  my $id = $data[0];

  my $x_start = $data[1];
  my $x_stop = $x_start + $data[3] - 1;
  my $y_stop = 999 - $data[2];
  my $y_start = 999 - ($data[2] + $data[4] - 1);

  my @x_range = ($x_start..$x_stop);
  my @y_range = ($y_start..$y_stop);

  # Return array references to access outside of function
  return ($id, \@x_range, \@y_range);
}
