#!/usr/bin/perl
use strict;
use warnings;

use Data::Dumper;
use List::Util ("none", "any");

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

my ($coordinates, $sizes) = &generate_hashed_coords(@lines);

my $count = 0;
for my $val (values(%$coordinates)) {
  if (scalar(@$val) >= 2) {
    $count++;
  }
}
print "Number of overlapping square inches: ".$count."\n";
# Part 2:

# Build a hash with counts of non-overlapping patches for each claim which has
# such a patch.
my %candidate_ids;
foreach my $val (values %$coordinates) {
  my $size = scalar(@$val);
  if ($size eq 1) {
    my $id = @$val[0];
    $candidate_ids{$id}++;
  }
}

# Check if a claim's number of non-overlapping patches is equal to its size.
foreach my $id (keys(%candidate_ids)) {
  if ($candidate_ids{$id} eq $sizes->{$id}) {
    print "Non-overlapping claim ID: $id\n";
  }
}

# =========== Subroutine declarations ============
sub generate_hashed_coords {
  my @lines = @_;
  my %mapped_coords;
  my %sizes;
  foreach my $line (@lines) {
    my ($id, $x, $y, $size) = &get_claim_info($line);
    $sizes{$id} = $size;
    for my $i (@$x) {
      for my $j (@$y) {
        my $k = "$i,$j"; #key
        # If the coord has been seen before, associate it with this claim's ID.
        if (exists($mapped_coords{$k})) {
          push(@{$mapped_coords{$k}}, $id);
        } else {
          # Otherwise initialize the array and associate with this claim's ID.
          my @arr = $id;
          # Store only reference to array
          $mapped_coords{$k} = \@arr;
        }
      }
    }
  }
  return (\%mapped_coords, \%sizes);
}

sub get_claim_info {
  # Splits claim string into useful info
  my $in = shift;
  my @data = $in =~/#(\d+)\s@\s(\d+),(\d+):\s(\d+)x(\d+)/;
  my $id = $data[0];
  my $size = $data[3]*$data[4];

  my $x_start = $data[1];
  my $x_stop = $x_start + $data[3] - 1;
  my $y_stop = 999 - $data[2];
  my $y_start = 999 - ($data[2] + $data[4] - 1);

  my @x_range = ($x_start..$x_stop);
  my @y_range = ($y_start..$y_stop);

  # Return array references to access outside of function
  return ($id, \@x_range, \@y_range, $size);
}
